import { Injectable } from '@nestjs/common';

import { UserService } from 'src/user/user.service';
import { SecurityService } from 'src/security/security.service';

@Injectable()
export class AuthService {
  constructor(
    private readonly userService: UserService,
    private readonly securityService: SecurityService,
  ) {}

  async login({
    email,
    password,
  }: {
    email: string;
    password: string;
  }): Promise<any> {
    const user = await this.userService.findByEmail(email);
    if (!user) {
      throw new Error('User not found');
    }

    const isValid = await this.securityService.validatePassword(
      password,
      user.password,
    );
    if (!isValid) {
      throw new Error('Invalid password');
    }

    delete user.password;

    const token = await this.securityService.generateToken();

    return { user, token };
  }
}
